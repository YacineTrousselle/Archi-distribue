import spacy
from fuzzywuzzy import fuzz


class SpacyProcessor:
    actions = {
        'play': ['jou', 'écout', 'lanc'],
        'pause': ['arrêt', 'stopp'],
        'search': ['cherch', 'recherch', 'trouv']
    }

    songs = ["killa", "monodrama", "horizon", "bury the light"]

    def __init__(self):
        self.nlp = spacy.load('fr_core_news_md')

    def process(self, text):
        doc = self.nlp(text.lower())

        verbs = list(filter(lambda token: token.pos_ == 'VERB', doc))
        action = self.get_action(verbs)

        return {'action': action, 'song_scores': self.get_song(doc)}

    def get_action(self, verbs):
        for verb in verbs:
            for action, action_verbs in self.actions.items():
                if any(verb.text.startswith(action_verb) for action_verb in action_verbs):
                    return action
        return 'unknown'

    def get_song(self, text):
        return sorted([(song, fuzz.partial_ratio(text, song)) for song in self.songs], key=lambda x: x[1], reverse=True)
