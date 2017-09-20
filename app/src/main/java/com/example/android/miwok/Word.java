package com.example.android.miwok;

/**
 * Created by nirmal vats on 6/7/2017.
 */

public class Word {

    private  String mmiwok;
    private String mdefault;
    private int mimageResourceid=NO_IMAGE_RESOURCE;
    private static final int NO_IMAGE_RESOURCE=-1;
    private int mAudioResourceId;

    public String getDefaultWord() {
        return mdefault;
    }

    public String getMiwok() {
        return mmiwok;
    }

    public int getImageResourceid(){
        return mimageResourceid;
    }

   public Word(String defaultword,String miwok,int AudioResourceId ){
       mmiwok = miwok;
       mdefault= defaultword;
       mAudioResourceId=AudioResourceId;
   }
   public int getAudioResourceId(){
       return mAudioResourceId;
   }

   //another consturctor for setting image resource id.

    public Word(String defaultWord, String miwok, int Resourceid ,int AudioResourceId){
        mdefault = defaultWord;
        mmiwok= miwok;
        mimageResourceid =Resourceid;
        mAudioResourceId= AudioResourceId;
    }

    public boolean hasImage(){
        return mimageResourceid!=NO_IMAGE_RESOURCE;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mmiwok='" + mmiwok + '\'' +
                ", mdefault='" + mdefault + '\'' +
                ", mimageResourceid=" + mimageResourceid +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
