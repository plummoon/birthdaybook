package lexiong.me.birthdaybook.model;

import java.util.Date;

/**
 * Created by yueli on 2018/4/2.
 * lexiong.me.birthdaybook.model
 */

public class Person {
    private String birthday;
    private String name;
    private String birthPlace;
    private String CreateTime;
    private String personId;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }
}
