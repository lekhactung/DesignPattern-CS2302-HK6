/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv3.utils.theme;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 *
 * @author LE TUNG
 */
public class ThemeManager {
    public static ThemeFactory themeFac = new DefaultThemeFactory();

    public static void setThemeFac(ThemeFactory f){
        themeFac = f;
    }
    
    public static void applyTheme(Scene s){
        s.getRoot().getStylesheets().clear();
        s.getRoot().getStylesheets().add(themeFac.getStyleSheet());
    }
    
}
