package com.example.week3day3hw;

import android.os.Parcel;
import android.os.Parcelable;

public class Animal implements Parcelable {
    private String type;
    private String name;
    private String sound;
    private String image;
    private int animalID;

    public Animal() {
    }

    public Animal(String type, String name, String sound, String image, int animalID) {
        this.type = type;
        this.name = name;
        this.sound = sound;
        this.image = image;
        this.animalID = animalID;
    }

    protected Animal(Parcel in) {
        type = in.readString();
        name = in.readString();
        sound = in.readString();
        image = in.readString();
        animalID = in.readInt();
    }

    public static final Creator<Animal> CREATOR = new Creator<Animal>() {
        @Override
        public Animal createFromParcel(Parcel in) {
            return new Animal(in);
        }

        @Override
        public Animal[] newArray(int size) {
            return new Animal[size];
        }
    };

    public int getAnimalID() {
        return animalID;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", sound='" + sound + '\'' +
                ", image='" + image + '\'' +
                ", animalID=" + animalID +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(name);
        dest.writeString(sound);
        dest.writeString(image);
        dest.writeInt(animalID);
    }
}
