import spacy
from fuzzywuzzy import fuzz
from pymongo import MongoClient


class SpacyProcessor:
    actions = {
        'play': ['jou', 'écout', 'lanc'],
        'pause': ['arrêt', 'stopp'],
        'search': ['cherch', 'recherch', 'trouv'],
        'up': ['mont'],
        'down': ['bais', 'rédui'],
    }

    def get_songs(self):
        client = MongoClient("mongodb://mongo:27017/")
        db = client.songs
        collection = db.songs
        songs = []
        for document in collection.find():
            songs.append({'id': str(document['_id']), 'Title': document['Title']})
        return songs

    def __init__(self):
        self.nlp = spacy.load('fr_core_news_md')

    def process(self, text):
        doc = self.nlp(text.lower())

        verbs = list(filter(lambda token: token.pos_ == 'VERB' or token.text.startswith('baiss'), doc))
        print(verbs)
        action = self.get_action(verbs)

        return {'action': action, 'song_scores': self.get_song(doc) if action == 'play' or action == 'search' else None}

    def get_action(self, verbs):
        for verb in verbs:
            for action, action_verbs in self.actions.items():
                if any(verb.text.startswith(action_verb) for action_verb in action_verbs):
                    return action
        return 'unknown'

    def get_song(self, text):
        return sorted([(song, fuzz.partial_ratio(text, song['Title'])) for song in self.get_songs()],
                      key=lambda x: x[1], reverse=True)
