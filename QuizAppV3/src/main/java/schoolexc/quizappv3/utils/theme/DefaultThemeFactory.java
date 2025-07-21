/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolexc.quizappv3.utils.theme;

import schoolexc.quizappv3.App;

/**
 *
 * @author LE TUNG
 */
public class DefaultThemeFactory implements ThemeFactory {

    @Override
    public String getStyleSheet() {
        return App.class.getResource("style.css").toExternalForm();
    }

}
