/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.ultils.Theme;

import javafx.scene.Scene;
import schoolexc.quizapp.App;

/**
 *
 * @author LE TUNG
 */
public class ThemeManager {

    private static ThemeFactory themeFac = new DefaulThemeFactory();

    public static void setThemeFac(ThemeFactory theme) {
        themeFac = theme;
    }

    public static void applyTheme(Scene s) {
        s.getRoot().getStylesheets().clear();
        s.getRoot().getStylesheets().add(themeFac.getStyleSheet());

    }
}
