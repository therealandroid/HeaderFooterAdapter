package br.com.diogojayme.autobindadapter.library;

import android.view.View;

import com.google.gson.internal.Primitives;

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
    public static <T extends GenericItemViewHolder> T generateViewHolder(Class<T> tClass, View view){
        try {
            Class<?> clazz= Class.forName(tClass.getName());
            Constructor<?> constructor = clazz.getConstructor(View.class);
            Object holder = constructor.newInstance(view);
            return Primitives.wrap(tClass).cast(holder);
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
