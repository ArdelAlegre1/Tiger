package com.example.ardelalegre.tiger.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import io.reactivex.annotations.NonNull;

@Entity(tableName = "foods")
public class Food {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    private int id;


    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "edate")
    private String edate;

    @ColumnInfo(name = "codeNumber")
    private String codeNumber;

    public Food() {

    }
    @Ignore
    public Food(String codeNumber, String name, String edate) {
        this.name = name;
        this.codeNumber = codeNumber;
        this.edate = edate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }
    @Override
    public String toString() {
        return new StringBuilder(name).append("\n").append("Expires: " + edate).toString();

    }
}
