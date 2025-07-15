/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv2.utils.theme;

import schoolexc.quizappv2.App;

/**
 *
 * @author LE TUNG
 */
public class DarkThemeFactory implements ThemeFactory{

    @Override
    public String getStyleSheet() {
        return App.class.getResource("dark.css").toExternalForm();
    }

    
}
