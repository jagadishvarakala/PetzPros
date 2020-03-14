package com.petz.pros.data.db.model;

import com.petz.pros.data.utils.DateConverter;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;


/**
 * Created on : Feb 01, 2019
 * Author     : AndroidWave
 */
@Entity(tableName = "user")
@TypeConverters({DateConverter.class})
public class User {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "user_Id")
    private int mUserId;

    @ColumnInfo(name = "first_name")
    private String mFirstName;

    @ColumnInfo(name = "last_name")
    private String mLastName;

    @ColumnInfo(name = "email")
    private String mEmail;

    @ColumnInfo(name = "mobile")
    private String mMobile;

    @ColumnInfo(name = "last_update")
    private Date mLastUpdate;

    @ColumnInfo(name = "created_at")
    private Date mCreatedAt;

    public User(int mUserId,String mFirstName, String mLastName, String mEmail, String mMobile, Date mLastUpdate, Date mCreatedAt) {
        this.mUserId = mUserId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mEmail = mEmail;
        this.mMobile = mMobile;
        this.mLastUpdate = mLastUpdate;
        this.mCreatedAt = mCreatedAt;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getMobile() {
        return mMobile;
    }

    public void setMobile(String mMobile) {
        this.mMobile = mMobile;
    }


    public Date getLastUpdate() {
        return mLastUpdate;
    }

    public void setLastUpdate(Date mLastUpdate) {
        this.mLastUpdate = mLastUpdate;
    }

    public Date getCreatedAt() {
        return mCreatedAt;
    }

    public void setCreatedAt(Date mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }

}
