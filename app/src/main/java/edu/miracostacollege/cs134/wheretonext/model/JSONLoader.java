package edu.miracostacollege.cs134.wheretonext.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class loads College data from a formatted JSON (JavaScript Object Notation) file.
 * Populates data model (College) with data.
 */
public class JSONLoader {

    /**
     * Loads JSON data from a file in the assets directory.
     *
     * @param context The activity from which the data is loaded.
     * @throws IOException If there is an error reading from the JSON file.
     */
    public static List<College> loadJSONFromAsset(Context context) throws IOException {
        List<College> allCollegesList = new ArrayList<>();
        String json = null;
        InputStream is = context.getAssets().open("Colleges.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");

        try {
            JSONObject jsonRootObject = new JSONObject(json);
            JSONArray allCountriesJSON = jsonRootObject.getJSONArray("Colleges");
            int numberOfColleges = allCountriesJSON.length();
            // DONE: Loop through all the colleges in the JSON data, create a College object for each
            // DONE: Add each college object to the list
            for (int i = 0; i < numberOfColleges ; i++) {
                JSONObject collegeEventJSON = allCountriesJSON.getJSONObject(i);

                College school = new College();

                school.setName(collegeEventJSON.getString("Name"));
                school.setPopulation(collegeEventJSON.getInt("Population"));
                school.setTuition(collegeEventJSON.getInt("Tuition"));
                school.setRating(collegeEventJSON.getDouble("Rating"));
                school.setImageName(collegeEventJSON.getString("FileName"));
                allCollegesList.add(school);
            }


        } catch (JSONException e) {
            Log.e("Where to Json adding", e.getMessage());
        }

        return allCollegesList;
    }
}
