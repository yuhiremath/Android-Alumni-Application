package reva.com.revaalumni.CardView;

import java.util.Date;

/**
 * Created by yashas.hiremath on 9/8/2016.
 */
public class Event {
    private String date;
    private String subject;
    private String body;
    private String key;
    private String approved;
    private String ref_key;

    public String getRef_key() {
        return ref_key;
    }

    public void setRef_key(String ref_key) {
        this.ref_key = ref_key;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {

        return key;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}
