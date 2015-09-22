package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private MyDatabaseHelper dbHelper;
    private Button create_db;
    private Button add_data;
    private Button update_data;
    private Button del_data;
    private Button query_data;
    private ContentValues values=new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        create_db= (Button) findViewById(R.id.create_datable);
        create_db.setOnClickListener(this);
        add_data= (Button) findViewById(R.id.add_data);
        add_data.setOnClickListener(this);
        update_data= (Button) findViewById(R.id.update_data);
        update_data.setOnClickListener(this);
        query_data= (Button) findViewById(R.id.query_data);
        query_data.setOnClickListener(this);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 3);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.create_datable){//创建数据库
            dbHelper.getWritableDatabase();
        }else if (v.getId()==R.id.add_data){//添加数据
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            values.put("name","The Da Vinci Code");
            values.put("author","Dan Brown");
            values.put("pages","454");
            values.put("price","16.96");
            db.insert("Book", null, values);

            values.clear();
            values.put("name", "The  Lost Symbol");
            values.put("author","Dan Brown");
            values.put("pages","510");
            values.put("price", "19.95");
            db.insert("Book", null, values);
            values.clear();
        }else if (v.getId()==R.id.update_data){//修改数据
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            values.put("price",10.10);
            db.update("Book",values,"name=?",new String[]{"The Da Vinci Code"});
        }else if (v.getId()==R.id.delete_data){
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            //db.delete("Book","page>?",new String[]{"500"});
            db.execSQL("delete from book where pages>?", new String[]{"400"});
        }else if (v.getId()==R.id.query_data){
            SQLiteDatabase db=dbHelper.getWritableDatabase();
            Log.i("asdasd","dasdas");

        }
    }
}
