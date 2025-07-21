/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package schoolexc.quizappv3.utils.theme;

/**
 *
 * @author LE TUNG
 */
public enum Theme {
    DARK {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFac(new DarkThemeFactory());
        }
    }, LIGHT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFac(new LightThemeFactory());
        }
    }, DEFAULT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFac(new DefaultThemeFactory());
        }
    };

    public abstract void updateTheme();
}
