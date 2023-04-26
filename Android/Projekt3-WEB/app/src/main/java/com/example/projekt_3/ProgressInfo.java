package com.example.projekt_3;

import android.os.Parcel;
import android.os.Parcelable;

public class ProgressInfo implements Parcelable{
    public int mPobranychBajtow;
    public int mRozmiar;
    public Status mStatus;

    public ProgressInfo(int mPobranychBajtow, int mRozmiar, Status mStatus) {
        this.mPobranychBajtow = mPobranychBajtow;
        this.mRozmiar = mRozmiar;
        this.mStatus = mStatus;
    }

    protected ProgressInfo(Parcel in) {
        mPobranychBajtow = in.readInt();
        mRozmiar = in.readInt();
        mStatus = Status.valueOf(in.readString());
    }

    public static final Creator<ProgressInfo> CREATOR = new Creator<ProgressInfo>() {
        @Override
        public ProgressInfo createFromParcel(Parcel in) { return new ProgressInfo(in); }

        @Override
        public ProgressInfo[] newArray(int size) { return new ProgressInfo[size]; }
    };

    public ProgressInfo() { }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mPobranychBajtow);
        parcel.writeInt(mRozmiar);
        parcel.writeString(mStatus.name());
    }

    enum Status {
        TRWA,
        ZAKONCZONE,
        BLAD
    }
}
