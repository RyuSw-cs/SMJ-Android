package com.example.smj.ui.main.fragment.Convenience.remote;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Meta implements Parcelable {

    @SerializedName("same_name")
    @Expose
    private SameName sameName;
    @SerializedName("pageable_count")
    @Expose
    private Integer pageableCount;
    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("is_end")
    @Expose
    private boolean isEnd;

    public SameName getSameName() {
        return sameName;
    }

    public void setSameName(SameName sameName) {
        this.sameName = sameName;
    }

    public Integer getPageableCount() {
        return pageableCount;
    }

    public void setPageableCount(Integer pageableCount) {
        this.pageableCount = pageableCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    protected Meta(Parcel in) {
        this.sameName = in.readParcelable(SameName.class.getClassLoader());
        this.pageableCount = (Integer)in.readValue(Integer.class.getClassLoader());
        this.totalCount = (Integer)in.readValue(Integer.class.getClassLoader());
        this.isEnd = (Boolean)in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel in) {
            return new Meta(in);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.sameName,flags);
        dest.writeValue(this.pageableCount);
        dest.writeValue(this.totalCount);
        dest.writeValue(this.isEnd);
    }
}
