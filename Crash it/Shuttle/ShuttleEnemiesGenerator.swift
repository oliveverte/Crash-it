//
//  ShuttleEnemiesGenerator.swift
//  Crash it
//
//  Created by Olivier Picard on 18/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class ShuttleEnemyGenerator {
    /** Nombre d'image de vaisseau comptenu dans l'atlas */
    private let NUMBER_OF_IMAGE_SHUTTLE: UInt32 = 5
    private let scene: SKScene
    private let target: ShuttlePlayer
    
    private var previous_generate_time: TimeInterval?
    private var maxTime_before_generate: TimeInterval = 0.5 // en seconde
    var enemies_percent: Int = 10
    
    
    
    init(scene: SKScene, target: ShuttlePlayer) {
        self.scene = scene
        self.target = target
        arc4random_stir()
    }
    
    func generate(_ currentTime: TimeInterval) {
        if currentTime - (previous_generate_time ?? 0) < maxTime_before_generate { return }
        self.previous_generate_time = currentTime
        if arc4random_uniform(101) > self.enemies_percent { return }
        
        
        let yPos = scene.size.height + 10
        let xPos = CGFloat(arc4random_uniform(UInt32(self.scene.size.width)))
        let image = randomEnemyShuttle(imageIdFrom: 1, imageIdTo: NUMBER_OF_IMAGE_SHUTTLE)
        let shuttle_infos = determineShuttleGlobalInformation(img: image)
        let shuttle = ShuttleEnemy(image: image,
                                   color: shuttle_infos.color,
                                   stats: shuttle_infos.stats,
                                   target: self.target)
        
        shuttle.position = CGPoint(x: xPos, y: yPos)
        self.scene.addChild(shuttle)
    }
    
    
    func randomEnemyShuttle(imageIdFrom: UInt32, imageIdTo: UInt32) -> UIImage {
        let imageName = "ennemy_" +
            String(arc4random_uniform(imageIdTo + 1 - imageIdFrom) + imageIdFrom)
        
        return UIImage(named: imageName)!
    }
    
    
    func determineShuttleGlobalInformation(img: UIImage) -> (stats: Shuttle.Stats, color: UIColor) {
        let stats: Shuttle.Stats
        let color: UIColor
        
        switch img {
        case #imageLiteral(resourceName: "ennemy_1"):
            stats = Shuttle.Stats(defense: 70, attack: 30,
                                  shootStats: Shuttle.Stats.ShootStats(deltaTimeToShoot: 1/5,
                                                                       probSuccessFire: 30))
            color = UIColor.orange
        case #imageLiteral(resourceName: "ennemy_2"):
            stats = Shuttle.Stats(defense: 80, attack: 10,
                                  shootStats: Shuttle.Stats.ShootStats(deltaTimeToShoot: 1/3,
                                                                       probSuccessFire: 40))
            color = UIColor.cyan
        case #imageLiteral(resourceName: "ennemy_3"):
            stats = Shuttle.Stats(defense: 120, attack: 10,
                                  shootStats: Shuttle.Stats.ShootStats(deltaTimeToShoot: 1/3,
                                                                       probSuccessFire: 30))
            color = UIColor.cyan
        case #imageLiteral(resourceName: "ennemy_4"):
            stats = Shuttle.Stats(defense: 230, attack: 10,
                                  shootStats: Shuttle.Stats.ShootStats(deltaTimeToShoot: 1/3,
                                                                       probSuccessFire: 40))
            color = UIColor.cyan
        case #imageLiteral(resourceName: "ennemy_5"):
            stats = Shuttle.Stats(defense: 70, attack: 20,
                                  shootStats: Shuttle.Stats.ShootStats(deltaTimeToShoot: 1/6,
                                                                       probSuccessFire: 20))
            color = UIColor.green
        default:
            stats = Shuttle.Stats(defense: 0, attack: 0, shootStats: Shuttle.Stats.ShootStats())
            color = UIColor.white
        }
        
        return (stats, color)
    }

    
}






















