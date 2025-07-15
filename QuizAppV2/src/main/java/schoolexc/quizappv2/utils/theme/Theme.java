/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package schoolexc.quizappv2.utils.theme;

/**
 *
 * @author LE TUNG
 */
public enum Theme {
    DEFAULT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFac(new DefaultThemeFactory());
        }

    },
    DARK {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFac(new DarkThemeFactory());
        }

    },
    LIGHT {
        @Override
        public void updateTheme() {
            ThemeManager.setThemeFac(new LightThemeFactory());
        }

    };

    public abstract void updateTheme();

}
