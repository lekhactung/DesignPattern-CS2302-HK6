/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.utils.theme;

import javafx.scene.Scene;
import schoolexc.quizappv2.App;

/**
 *
 * @author LE TUNG
 */
public class ThemeManager {

    private static ThemeFactory themeFactory = new DefaultThemeFactory();

    public static void setThemeFac(ThemeFactory f) {
        themeFactory = f;
    }

    public static void applyTheme(Scene s) {
        s.getRoot().getStylesheets().clear();
        s.getRoot().getStylesheets().add(themeFactory.getStyleSheet());
    }
}
