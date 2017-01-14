package com.pissay.chatra.network;

/**
 * Created by S.K. Pissay on 5/3/16.
 */
public class Constants {

    public static final String AUTHKEY = "user-key";
    public static final String LOCATIONS_API = "locations";
    public static final String LOCATION_DETAILS = "location_details";
    public static final String SEARCH_QUERY = "q";

    public static final String ENTITY_ID = "entity_id";
    public static final String ENTITY_TYPE = "entity_type";
    public static final String LATITUDE = "lat";
    public static final String LONGITUDE = "lon";

    private String API_KEY = "07b2294e08dd61ee580be44c23a91c1b";

    public String getApiKey() {
        return API_KEY;
    }

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final String AUTHLOGIN = "auth/login";
    public static final String OTP_VERIFY = "auth/otp/verify";
    public static final String LEADSLIST = "leads";
    public static final String CUSTOMER = "customer";
    public static final String LEAD = "leads";
    public static final String CUSTOMERS = "customers";
    public static final String LANDING = "home";
    public static final String CAMPAIGNS = "campaigns";
    public static final String ATTENDANCES = "attendances";
    public static final String ATTENDANCE_CALENDAR = "attendances/calendar";
    public static final String AUTHLOGOUT = "auth/logout";
    public static final String PDF_UPLOAD = "document";
    public static final String PHONE = "phone";
    public static final String OTP = "otp";

    public static final String TYPE_WEDDING = "typeWedding";
    public static final String TYPE_ENGAGEMENT = "typeEngagement";
    public static final String TYPE_BIRTHDAY = "typeBirthday";
    public static final String TYPE_NAMING = "typeNaming";
    public static final String TYPE_CORPORATE = "typeCorporate";
    public static final String TYPE_PARTY = "typeParty";

    public static final String FILTER_PLACE = "filterPlace";
    public static final String FILTER_DATE = "filterDate";
    public static final String FILTER_SESSION = "filterSession";

    //old Struct
//    public static final String PROJECTS = "projects";
//    public static final String BANKSAPPROVING = "banks/approving";
//    public static final String PROJECTSAPPROVAL = "projects/to-be-approved";

    //new Struct
    public static final String HALLS_LIST = "events";
    public static final String LOGIN = "login/auth";
    public static final String INSCLUDE = "include";
    public static final String MESSAGES = "messages";
    public static final String DATA = "data";


    public static String apiMethodEx(String apiMethod, String uuid) {
        StringBuffer lBuf = new StringBuffer();
        if (null != uuid) {
            return lBuf.append(apiMethod).append("/").append(uuid).toString();
        } else {
            return lBuf.append(apiMethod).toString();
        }
    }
}
