package jansen.thomas.restaurant;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Callback activity1;
    private Context context1;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = error.getMessage();
        activity1.gotCategoriesError(errorMessage);
    }

    @Override
    public void onResponse(JSONObject response) {
        JSONArray arrayCategories = null;
        try {
            arrayCategories = response.getJSONArray("categories");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> arrayListCategories = new ArrayList<String>();
        for (int i = 0; i < arrayCategories.length(); i++) {
            try {
                arrayListCategories.add(arrayCategories.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        activity1.gotCategories(arrayListCategories);
    }

    public CategoriesRequest(Context context) {
        context1 = context;
    }

    public void getCategories(Callback activity) {

        activity1 = activity;
        String url = "https://resto.mprog.nl/categories";

        RequestQueue queue = Volley.newRequestQueue(context1);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, this, this);
        queue.add(jsonObjectRequest);
    }
}
