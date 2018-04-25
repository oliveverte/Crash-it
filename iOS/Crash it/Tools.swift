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
    
    @objc(Tools_ItemConf)class ItemConf: NSObject, NSCoding {
        var type: String?
        var position: CGPoint?
        var image: String?
        var zPosition: CGFloat?
        var zRotation: CGFloat?
        var life: Int?
        var dx: CGFloat?
        var dy: CGFloat?
        
        init(_ type: String?, _ position: CGPoint? , _ image: String?, _ zPosition: CGFloat?, _ zRotation: CGFloat?, _ life: Int?, _ direction: CGVector?) {
            self.type = type
            self.position = position
            self.image = image
            self.zPosition = zPosition
            self.zRotation = zRotation
            self.life = life
            self.dx = direction?.dx
            self.dy = direction?.dy
        }
        
        required convenience init?(coder aDecoder: NSCoder) {
            let type = aDecoder.decodeObject(forKey: "type") as? String
            let position = aDecoder.decodeObject(forKey: "position") as? CGPoint
            let image = aDecoder.decodeObject(forKey: "image") as? String
            let zPosition = aDecoder.decodeObject(forKey: "zPosition") as? CGFloat
            let zRotation = aDecoder.decodeObject(forKey: "zRotation") as? CGFloat
            let life = aDecoder.decodeObject(forKey: "life") as? Int
            let dx = aDecoder.decodeObject(forKey: "dx") as? CGFloat
            let dy = aDecoder.decodeObject(forKey: "dy") as? CGFloat
            self.init(type, position, image, zPosition, zRotation, life, CGVector(dx: dx!, dy: dy!))
        }
        
        func encode(with aCoder: NSCoder) {
            aCoder.encode(type, forKey: "type")
            aCoder.encode(position, forKey: "position")
            aCoder.encode(image, forKey: "image")
            aCoder.encode(zPosition, forKey: "zPosition")
            aCoder.encode(zRotation, forKey: "zRotation")
            aCoder.encode(life, forKey: "life")
            aCoder.encode(dx, forKey: "dx")
            aCoder.encode(dy, forKey: "dy")
        }
    }
    
    @objc(Tools_SaveData)class SaveData: NSObject, NSCoding {
        var date: Date?
        var score: Int?
        var items: [ItemConf]?
        
        init(_ date: Date?, _ score: Int?, _ items: [ItemConf]?) {
            self.date = date
            self.score = score
            self.items = items
        }
        
        required convenience init?(coder aDecoder: NSCoder) {
            let date = aDecoder.decodeObject(forKey: "date") as? Date
            let score = aDecoder.decodeObject(forKey: "score") as? Int
            let items = aDecoder.decodeObject(forKey: "items") as? [ItemConf]
            self.init(date, score, items)
        }
        
        func encode(with aCoder: NSCoder) {
            aCoder.encode(date, forKey: "date")
            aCoder.encode(score, forKey: "score")
            aCoder.encode(items, forKey: "items")
        }
        
    }
    
    
    //----------------- CLASS TOOLS -------------------
    
    
    
    
    
    
    static var scene_size: CGSize?
    static let KEY_DEFAULT_SCORES = "scores"
    static let KEY_DEFAULT_GAMEINFOS = "gameInfos"
    static let KEY_DEFAULT_GAME_ITEMS = "GameItems"
    
    
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
    static func addEncodedSaveDatas(_ key: String, _ score: Int, _ data: [ItemConf]?) -> Data {
        var listSavedData: [Tools.SaveData] = []
        if let encoded = UserDefaults.standard.object(forKey: key) {
            listSavedData = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        }
        let saveData = Tools.SaveData.init(Date(), score, data)
        listSavedData.append(saveData)
        return NSKeyedArchiver.archivedData(withRootObject: listSavedData)
    }
    

    
    
    
    
    

    
}















