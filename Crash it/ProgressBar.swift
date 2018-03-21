//
//  ProgressBar.swift
//  Crash it
//
//  Created by Olivier Picard on 21/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class ProgressBar: SKSpriteNode {
    var background: SKSpriteNode
    var maxValue: Int
    private var _nonEditedPosition: CGPoint
    private var _value: Int
    
    var value: Int {
        get { return self._value }
        set {
            self._value = newValue
            updateView()
        }
    }
    
    // On surcharge l'attibut position pour qu'on puisse
    // sauvegarder l'emplacement de la barre lorsqu'elle
    // est modifié depuis l'extérieur de la classe
    // (Et ainsi la replacé correctement après l'updateView)
    override var position: CGPoint {
        didSet {
            self._nonEditedPosition = position
        }
    }
    
    
    
    
    
    init(maxValue: Int, size: CGSize) {
        self.maxValue = maxValue
        self._value = 0
        self._nonEditedPosition = CGPoint.zero
        self.background = SKSpriteNode(texture: nil, color: UIColor.gray, size: size)
        super.init(texture: nil, color: UIColor.green, size: size)
        self.background.zPosition = -1
        self.addChild(self.background)
    }
    
    convenience init() {
        self.init(maxValue: 100, size: CGSize.zero)
    }
    
    
    required init?(coder aDecoder: NSCoder) {
        self.maxValue = 100
        self._value = 0
        self.background = SKSpriteNode()
        self._nonEditedPosition = CGPoint.zero
        super.init(coder: aDecoder)
    }
    
    private func updateView() {
        let percent:CGFloat = CGFloat(self._value) * 100 / CGFloat(self.maxValue)
        let updatedWidth = self.background.size.width * percent / 100
        self.size.width = updatedWidth
        // remet le fond en position initial
        self.background.position.x = 0
        // remet la barre de progression en position par default
        self.position.x = self._nonEditedPosition.x
        
        // Sauvegarde la position avant les modification, pour replacer le fond
        let savedPosition = self.position
        // Centre le point pivot (0.5, 0.5) sur le coté gauche du fond
        let centerOnLeftSide = self.position.x - self.background.size.width/2
        // Aligne le côté gauche de la barre de progression sur le côté gauche du fond
        // A cette étape la barre de progression est bien positionné
        let alignedTotheLeft = centerOnLeftSide + self.size.width/2
        
        // A noté que le fond étant un noeud enfant se déplace avec la barre de progression
        // Il faut donc le recentré par rapport à sa position initial
        
        // Distance entre la position (point pivot: 0.5, 0.5) actuel du fond et la position initial
        let distanceToCorrectBackgroundPosition = savedPosition.x - alignedTotheLeft
        // On translate le fond de la distance calculé pour quel se soit à sa position initial
        // sans que la barre de progression ne bouge
        let centeredBackground = self.background.position.x + distanceToCorrectBackgroundPosition
        
        // On applique les valeurs calculé
        self.position.x = alignedTotheLeft
        self.background.position.x = centeredBackground
    }
    
}












