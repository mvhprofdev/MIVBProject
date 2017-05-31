package be.ehb.mivbproject.source;

/**
 * Created by mobapp10 on 11/05/17.
 */

public class Agency {

    public String agency_name, agency_url, agency_timezone, agency_lang, agency_phone;

    public Agency(String str) {
        String[] temp = str.split(",");

        this.agency_name = temp[0];
        this.agency_url = temp[1];
        this.agency_timezone = temp[2];
        this.agency_lang = temp[3];
        this.agency_phone = temp[4];
    }

    public Agency() {
    }

    public String getAgency_name() {
        return agency_name;
    }

    public void setAgency_name(String agency_name) {
        this.agency_name = agency_name;
    }

    public String getAgency_url() {
        return agency_url;
    }

    public void setAgency_url(String agency_url) {
        this.agency_url = agency_url;
    }

    public String getAgency_timezone() {
        return agency_timezone;
    }

    public void setAgency_timezone(String agency_timezone) {
        this.agency_timezone = agency_timezone;
    }

    public String getAgency_lang() {
        return agency_lang;
    }

    public void setAgency_lang(String agency_lang) {
        this.agency_lang = agency_lang;
    }

    public String getAgency_phone() {
        return agency_phone;
    }

    public void setAgency_phone(String agency_phone) {
        this.agency_phone = agency_phone;
    }
}
