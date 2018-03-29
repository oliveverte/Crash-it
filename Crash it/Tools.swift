//
//  Tools.swift
//  Crash it
//
//  Created by Olivier Picard on 18/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import SpriteKit

class Tools {
    struct Interval {
        let min: UInt32
        let max: UInt32
        
        init(min: UInt32, max: UInt32) {
            self.min = min
            self.max = max
        }
        
        func random() -> Int {
            return Int(arc4random_uniform(max - min + 1) + min)
        }
    }
    
    
    @objc(_TtCC8Crash_it5Tools8SaveData)class SaveData: NSObject, NSCoding {
        var date: Date?
        var score: Int?
        
        init(_ date: Date?, _ score: Int?) {
            self.date = date
            self.score = score
        }
        
        required convenience init?(coder aDecoder: NSCoder) {
            let date = aDecoder.decodeObject(forKey: "date") as? Date
            let score = aDecoder.decodeObject(forKey: "score") as? Int
            self.init(date, score)
        }
        
        func encode(with aCoder: NSCoder) {
            aCoder.encode(date, forKey: "date")
            aCoder.encode(score, forKey: "score")
        }
        
        
    }
    
    static var scene_size: CGSize?
    static let KEY_DEFAULT_SCORES = "scores"
    static let KEY_DEFAULT_GAMEINFOS = "gameInfos"
    
    
    /**
     Convert a percentage of the current scene to a real point on screen (physic pixel coordinate).
     
     - returns:
     CGPoint represent coordinate of a point on screen (pixel).
     
     - parameters:
     - pos: CGpoint: between [0,1] on the scene (percentage of the scene).
     */
    static func fromSceneToWorldPosition(screenSpacePos pos: CGPoint) -> CGPoint {
        return CGPoint(x: (Tools.scene_size?.width)! * pos.x,
                       y: (Tools.scene_size?.height)! * pos.y)
    }
    
    
    static func fromSceneToWorldSize(sceneSpaceSize size: CGSize) -> CGSize {
        return CGSize(width: (Tools.scene_size?.width)! * size.width,
                      height: (Tools.scene_size?.height)! * size.height)
    }
    
    static func splitAtlas(atlas: SKTextureAtlas,
                           baseName name: String) -> [SKTexture]{
        var textures:[SKTexture] = []
        let nbSprite = atlas.textureNames.count
        for i in 1...nbSprite {
            textures.append(atlas.textureNamed(name+"\(i)"))
        }
        return textures
    }
    
    
    /**
     Sauvegarde le score actuel.
    */
    static func addEncodedSaveDatas(_ key: String, _ score: Int) -> Data {
        var listSavedData: [Tools.SaveData] = []
        if let encoded = UserDefaults.standard.object(forKey: key) {
            listSavedData = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        }
        let saveData = Tools.SaveData.init(Date(), score)
        listSavedData.append(saveData)
        return NSKeyedArchiver.archivedData(withRootObject: listSavedData)
    }
    
    
    
    
    

    
}















