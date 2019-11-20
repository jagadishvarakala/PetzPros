package com.petz.pros.data.network.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationRequest {


    @Expose
    @SerializedName("PetDetails")
    private PetDetails PetDetails;
    @Expose
    @SerializedName("ServiceIdList")
    private String ServiceIdList;
    @Expose
    @SerializedName("UserType")
    private String UserType;
    @Expose
    @SerializedName("IfscCode")
    private String IfscCode;
    @Expose
    @SerializedName("Branch")
    private String Branch;
    @Expose
    @SerializedName("BankName")
    private String BankName;
    @Expose
    @SerializedName("AccountNumber")
    private String AccountNumber;
    @Expose
    @SerializedName("UserRefTypeID")
    private int UserRefTypeID;
    @Expose
    @SerializedName("IsWalkerAvailable")
    private boolean IsWalkerAvailable;
    @Expose
    @SerializedName("ChargePerHour")
    private int ChargePerHour;
    @Expose
    @SerializedName("ZipCode")
    private int ZipCode;
    @Expose
    @SerializedName("Country")
    private String Country;
    @Expose
    @SerializedName("State")
    private String State;
    @Expose
    @SerializedName("City")
    private String City;
    @Expose
    @SerializedName("Address")
    private String Address;
    @Expose
    @SerializedName("Phone")
    private String Phone;
    @Expose
    @SerializedName("Password")
    private String Password;
    @Expose
    @SerializedName("EmailId")
    private String EmailId;
    @Expose
    @SerializedName("LastName")
    private String LastName;
    @Expose
    @SerializedName("FirstName")
    private String FirstName;
    @Expose
    @SerializedName("Id")
    private int Id;

    public void setPetDetails(PetDetails PetDetails) {
        this.PetDetails = PetDetails;
    }

    public void setServiceIdList(String ServiceIdList) {
        this.ServiceIdList = ServiceIdList;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public void setIfscCode(String IfscCode) {
        this.IfscCode = IfscCode;
    }

    public void setBranch(String Branch) {
        this.Branch = Branch;
    }

    public void setBankName(String BankName) {
        this.BankName = BankName;
    }

    public void setAccountNumber(String AccountNumber) {
        this.AccountNumber = AccountNumber;
    }

    public void setUserRefTypeID(int UserRefTypeID) {
        this.UserRefTypeID = UserRefTypeID;
    }

    public void setIsWalkerAvailable(boolean IsWalkerAvailable) {
        this.IsWalkerAvailable = IsWalkerAvailable;
    }

    public void setChargePerHour(int ChargePerHour) {
        this.ChargePerHour = ChargePerHour;
    }

    public void setZipCode(int ZipCode) {
        this.ZipCode = ZipCode;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setEmailId(String EmailId) {
        this.EmailId = EmailId;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public static class PetDetails {
        @Expose
        @SerializedName("UserId")
        private int UserId;
        @Expose
        @SerializedName("About")
        private String About;
        @Expose
        @SerializedName("IsFriendly")
        private boolean IsFriendly;
        @Expose
        @SerializedName("AgeInYears")
        private int AgeInYears;
        @Expose
        @SerializedName("Breed")
        private String Breed;
        @Expose
        @SerializedName("Name")
        private String Name;
        @Expose
        @SerializedName("Type")
        private String Type;
        @Expose
        @SerializedName("Id")
        private int Id;

        public void setUserId(int UserId) {
            this.UserId = UserId;
        }

        public void setAbout(String About) {
            this.About = About;
        }

        public void setIsFriendly(boolean IsFriendly) {
            this.IsFriendly = IsFriendly;
        }

        public void setAgeInYears(int AgeInYears) {
            this.AgeInYears = AgeInYears;
        }

        public void setBreed(String Breed) {
            this.Breed = Breed;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public void setType(String Type) {
            this.Type = Type;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }
}
