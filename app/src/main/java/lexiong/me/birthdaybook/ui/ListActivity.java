package lexiong.me.birthdaybook.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lexiong.me.birthdaybook.R;
import lexiong.me.birthdaybook.model.Person;

public class ListActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.birthday_listview);
        //为ListView增加一个HeadView(避免第一个Item被toolbar遮挡)
        //abc_action_bar_default_height_material属性获取系统actionBar的高度
        View headView = new View(this);
        headView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
                (int)getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));
        listView.addHeaderView(headView);
        listView.setOnItemClickListener(mMessageClickedHandler);
        loadListData();
    }

    private void loadListData() {
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();


        for (Person stu : getPersonData(30)) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("name", stu.getName());
            item.put("birthday", stu.getBirthday());
            item.put("personId", stu.getPersonId());
            data.add(item);
        }

        SimpleAdapter spa = new SimpleAdapter(this, data, R.layout.list_item,
                new String[]{"name", "birthday", "personId"}, new int[]{R.id.name, R.id.birthday, R.id.personId});

        listView.setAdapter(spa);
    }

    /**
     * Get Person
     *
     * @param size
     * @return
     */
    private List<Person> getPersonData(int size) {
        List<Person> personList = new ArrayList<>();

        Date dateNow = new Date();
        for (int i = 0; i < size; i++) {
            Person person = new Person();
            person.setBirthday(lexiong.me.birthdaybook.utils.DateUtils.getDateTimeString(dateNow));
            person.setName(i+"001Boy");
            person.setPersonId(String.valueOf(i + 1));
            personList.add(person);
        }

        return personList;
    }

    // Create a message handling object as an anonymous class.
    private ListView.OnItemClickListener mMessageClickedHandler = new ListView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            final TextView tv = (TextView)v.findViewById(R.id.name);
            Toast.makeText(getApplicationContext(),
                    "You just click the name :"+ tv.getText(), Toast.LENGTH_SHORT).show();
            // 取出点击listview某一项的内容
        }
    };

}
