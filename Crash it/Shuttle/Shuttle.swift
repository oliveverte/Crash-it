//
//  Player.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class Shuttle : MovingItem {
    struct Stats {
        struct ShootStats {
            /** Probabilité que la tentative de tire réussie, utile pour configurer l'IA.
             Plus le nombre est élevé plus le vaisseau tirera successivement et
             atteindra rapidement son nombre max de tires par seconde (rate_of_fire)*/
            var prob_success_fire: UInt32?
            /** Durée à attendre après chaque tire avant de pouvoir tirer à nouveau.
             Si c'est égale à 0 alors on peut tirer à volonter (exprimé en seconde)*/
            var delta_time_to_shoot: TimeInterval
            private var time_since_last_shoot: TimeInterval
            
            /**
             - Parameters:
                - deltaTimeToShoot:  Durée à attendre après chaque tire, avant de pouvoir tirer à nouveau.
             nil = pas de limite
                - probSuccessFire : Probabilité que la tentative de tire réussie
             */
            init(deltaTimeToShoot: TimeInterval?, probSuccessFire: UInt32?) {
                self.prob_success_fire = probSuccessFire
                self.delta_time_to_shoot = deltaTimeToShoot ?? 0
                self.time_since_last_shoot = 0
            }
            
            /** Raccourcis pour créer la structure en laissant tous non initialisées,
             pas de temps d'attente après les tire, les tires s'exécute à tous les coup
             */
            init() {
                self.prob_success_fire = nil
                self.delta_time_to_shoot = 0
                self.time_since_last_shoot = 0
            }
            
            /**
             Permet de savoir si le vaisseau est autorisé à tirer.
             - Important:
             Cette fonction doit être appelée à chaque update de la scene
             */
            mutating func canShoot(_ currentTime: TimeInterval) -> Bool {
                if currentTime - self.time_since_last_shoot < self.delta_time_to_shoot { return false }
                self.time_since_last_shoot = currentTime
                if arc4random_uniform(101) > self.prob_success_fire! { return false }
                return true
            }
        }
        
        let defense: Int
        let attack: Int
        var shoot_stats: ShootStats
        let linked_bonus: [Bonus]
        
        init(defense: Int, attack: Int, shootStats: ShootStats) {
            self.defense = defense
            self.attack = attack
            self.shoot_stats = shootStats
            self.linked_bonus = []
        }
        
        init(stats: Shuttle.Stats) {
            self.init(defense: stats.defense, attack: stats.attack, shootStats: stats.shoot_stats)
        }
    }
    
    
    
    
    //---------------- Début de la class Shuttle---------------
    
    
    
    var stats: Shuttle.Stats
    
    
    init(image: UIImage, color: UIColor, stats: Shuttle.Stats) {
        self.stats = stats
        super.init(texture: SKTexture.init(image: image),
                  color: color,
                  size: Tools.fromSceneToWorldSize(sceneSpaceSize: CGSize(width: 0.1, height: 0.05)),
                  speedFactor: 1.5,
                  direction: CGVector(dx: 0, dy: -1))
    }
    
    required init?(coder aDecoder: NSCoder) {
        self.stats = Shuttle.Stats(defense: 0, attack: 0, shootStats: Shuttle.Stats.ShootStats())
        super.init(coder: aDecoder)
    }

    
    /** Crée un rayon laser, à l'emplacement du vaisseau, et l'ajoute à la scene */
    func shoot(direction: CGVector, rotation: CGFloat) {
        let laser = LaserShot(color: self.color, direction: direction, rotation: rotation)
        laser.position = self.position
        self.scene!.addChild(laser)
    }
    
    
    
}
