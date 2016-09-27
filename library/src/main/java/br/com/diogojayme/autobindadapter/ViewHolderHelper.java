package br.com.diogojayme.autobindadapter;

import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by diogojayme on 8/29/16.
 */
public class ViewHolderHelper {

    /**
     * Generating View Holder
     * @param tClass
     * @param view
     * @return The view holder of the parameter
     */
    public static Object generateViewHolder(Class tClass, View view){
        try {
            //TODO Inner ViewHolder Classes doesn't work
            Class<?> clazz= Class.forName(tClass.getName());
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
