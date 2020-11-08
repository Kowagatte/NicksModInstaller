package ca.damocles.graphics

import ca.damocles.graphics.panels.MainScreen
import javax.swing.JFrame

class Frame(title: String): JFrame(title){

    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        contentPane = MainScreen()
        setLocationRelativeTo(null)
        isResizable = false
        pack()
        isVisible = true
    }

}