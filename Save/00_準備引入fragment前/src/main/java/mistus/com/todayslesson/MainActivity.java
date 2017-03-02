package mistus.com.todayslesson;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button startButton ;
    Button cancelButton ;
    Intent intent ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);
        intent = new Intent(MainActivity.this, ReminderIconService.class);

        startButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);
    }


    private  Button.OnClickListener buttonClickListener = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

                case R.id.startButton:
                    Log.e("System：", "StartClicked");
                    startService(intent);
                    break;

                case R.id.cancelButton:
                    Log.e("System：", "StopClicked");
                    stopService(intent);
                    break;

            }
        }
    };
}
