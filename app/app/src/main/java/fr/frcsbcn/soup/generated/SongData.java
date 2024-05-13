//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `Song.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package fr.frcsbcn.soup.generated;

public class SongData implements Cloneable,
                                 java.io.Serializable
{
    public String id;

    public String title;

    public String[] artists;

    public int filesize;

    public SongData()
    {
        this.id = "";
        this.title = "";
        this.filesize = 0;
    }

    public SongData(String id, String title, String[] artists, int filesize)
    {
        this.id = id;
        this.title = title;
        this.artists = artists;
        this.filesize = filesize;
    }

    public boolean equals(Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        SongData r = null;
        if(rhs instanceof SongData)
        {
            r = (SongData)rhs;
        }

        if(r != null)
        {
            if(this.id != r.id)
            {
                if(this.id == null || r.id == null || !this.id.equals(r.id))
                {
                    return false;
                }
            }
            if(this.title != r.title)
            {
                if(this.title == null || r.title == null || !this.title.equals(r.title))
                {
                    return false;
                }
            }
            if(!java.util.Arrays.equals(this.artists, r.artists))
            {
                return false;
            }
            if(this.filesize != r.filesize)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::Soup::SongData");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, id);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, title);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, artists);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, filesize);
        return h_;
    }

    public SongData clone()
    {
        SongData c = null;
        try
        {
            c = (SongData)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.id);
        ostr.writeString(this.title);
        ostr.writeStringSeq(this.artists);
        ostr.writeInt(this.filesize);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.id = istr.readString();
        this.title = istr.readString();
        this.artists = istr.readStringSeq();
        this.filesize = istr.readInt();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, SongData v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public SongData ice_read(com.zeroc.Ice.InputStream istr)
    {
        SongData v = new SongData();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<SongData> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, SongData v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<SongData> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(SongData.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final SongData _nullMarshalValue = new SongData();

    /** @hidden */
    public static final long serialVersionUID = 686774275L;
}