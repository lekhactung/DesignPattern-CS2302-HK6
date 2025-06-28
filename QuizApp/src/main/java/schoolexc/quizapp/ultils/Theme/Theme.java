/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package schoolexc.quizapp.ultils.Theme;

/**
 *
 * @author LE TUNG
 */
public enum Theme {
    DEFAULT {
        @Override
        public void updTheme() {
            ThemeManager.setThemeFac(new DefaulThemeFactory());
        }

    }, DARK {
        @Override
        public void updTheme() {
            ThemeManager.setThemeFac(new DarkThemeFactory());

        }

    }, LIGHT {
        @Override
        public void updTheme() {
            ThemeManager.setThemeFac(new LightThemeFactory());
        }
    };

    public abstract void updTheme();
}
