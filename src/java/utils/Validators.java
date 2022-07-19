/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author winne
 */
public class Validators {
    public static int convertFloat2Int(float f){
        return Math.round (f * 1f);
    }
    public static float convertInt2Float(int i){
        if (i == 0) return 0f;
        return i / 1f;
    }
}
