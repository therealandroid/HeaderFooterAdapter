package therealandroid.github.com.headerfooteradapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by diogojayme on 14/08/17.
 */

public class ReflectionUtil {

    /**
     * Instantiate a ViewHolder class
     *
     * @param tClass the classViewHolder
     * @param view   the parameter of the view
     * @return the class instantiated
     *
     * Limitations: tClass cannot be inner class
     */
    public static Object newInstance(Class<? extends RecyclerView.ViewHolder> tClass, View view) {
        try {
            Class<?> clazz = Class.forName(tClass.getName());
            Constructor<?> constructor = clazz.getConstructor(View.class);
            return constructor.newInstance(view);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
