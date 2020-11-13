package com.example.vijaya.androidhardware;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StorageActivity extends AppCompatActivity {
    EditText txt_content;
    EditText contenttoDisplay;
    String FILENAME = "MyAppStorage";
    private File pathSave;
    EditText caps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        txt_content = (EditText) findViewById(R.id.id_txt_mycontent);
        contenttoDisplay = (EditText) findViewById(R.id.id_txt_display);
        caps = findViewById(R.id.caps);
        pathSave = new File(getFilesDir(), FILENAME);
    }

    /**
     * Saving the data to the file, taken from text filed.
     * @param v
     * @throws IOException
     */
    public void saveTofile(View v) throws IOException {

        // ICP Task4: Write the code to save the text
        //The data from the text filed is read and appended with space.
        String text = txt_content.getText().toString();
        // The new file created at the filepath is open using a fileoutputstream and text data is written into it.

        String capsStr =caps.getText().toString();
        if(capsStr.equalsIgnoreCase("caps"))
        {
           text =  text.toUpperCase();

        }
        text += " ";
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(pathSave, true);
            fos.write(text.getBytes());
            txt_content.getText().clear();                  //The text filed's data is cleared
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Reading the text written in the file.
     * @param v
     * @throws IOException
     */
    public void retrieveFromFile(View v) throws IOException {

        // ICP Task4: Write the code to display the above saved text

        //An input stream for the file in the path where the data is saved earlier is accessed.
        FileInputStream fis = null;
        try {
            System.out.println("pathSave = "+ pathSave );
            fis = new FileInputStream(pathSave);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {                // The text data from the file is read
                sb.append(text).append("\n");                       //All the linea re read.
            }
            System.out.println("contenttoDisplay= " + sb.toString() );
            contenttoDisplay.setText(sb.toString());                // The text data is set into the text view field.
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
