/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizapp.ultils.Theme;

import schoolexc.quizapp.App;

/**
 *
 * @author LE TUNG
 */
public class LightThemeFactory implements ThemeFactory {

    @Override
    public String getStyleSheet() {
        return App.class.getResource("light.css").toExternalForm();
    }

}
