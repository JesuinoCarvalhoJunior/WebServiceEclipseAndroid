package com.example.junior_carvalho.webserviceeclipseandroid.LoginMetodosTestes;

import android.support.test.runner.AndroidJUnit4;

import com.example.junior_carvalho.webserviceeclipseandroid.MainAcitivity;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Junior_Carvalho on 25/12/2015.
 */
@RunWith(AndroidJUnit4.class)
public class isEmptyFieldsTeste extends MainAcitivity{




    @Test
    public void isEmpty(){
     boolean resultado = isEmptyFields("","");
        Assert.assertFalse(resultado);
    }


}
