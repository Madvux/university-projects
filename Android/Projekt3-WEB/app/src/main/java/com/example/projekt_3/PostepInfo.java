package com.example.projekt_3;


import android.os.Parcel;
import android.os.Parcelable;

public class PostepInfo implements Parcelable {
    public int mPobranychBajtow;
    public int mRozmiar;
    public int mStatus;

    protected PostepInfo(Parcel in) {
        mPobranychBajtow = in.readInt();
        mRozmiar = in.readInt();
        mStatus = in.readInt();
    }

    public static final Creator<PostepInfo> CREATOR = new Creator<PostepInfo>() {
        @Override
        public PostepInfo createFromParcel(Parcel in) {
            return new PostepInfo(in);
        }

        @Override
        public PostepInfo[] newArray(int size) {
            return new PostepInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mPobranychBajtow);
        parcel.writeInt(mRozmiar);
        parcel.writeInt(mStatus);
    }
}
