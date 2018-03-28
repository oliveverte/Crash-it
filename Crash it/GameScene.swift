//
//  GameScene.swift
//  Crash it
//
//  Created by Olivier Picard on 11/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import SpriteKit
import GameplayKit

class GameScene: SKScene {
    enum GameState {
        case play
        case gameOver
        case welcome
    }
    
    private let PLAYER_MOVING_SPEED:CGFloat = 3
    private var player: ShuttlePlayer!
    private var score_label = SKLabelNode()
    private var starsGenerator_topLayer:StarsGenerator!
    private var starsGenerator_bottomLayer:StarsGenerator!
    private var shuttle_enemy_generator: ShuttleEnemyGenerator!
    private var asteroids_generator: AsteroidsGenerator!
    private var gameOver_screen: GameOverScreen!
    private var welcome_screen: WelcomeScreen!
    private var state: GameState!
    internal var view_Controller: UIViewController!
    internal var userDefault: UserDefaults!
    
    
    
    var score: Int {
        get { return Int(self.score_label.text!)! }
        set {
            score_label.text! = String(newValue)
        }
    }
    
    override func didMove(to view: SKView) {
        Tools.scene_size = self.size
        self.score_label = self.childNode(withName: "Score") as! SKLabelNode
        self.score_label.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.8))
        self.score_label.text! = "0"
        self.score_label.isHidden = true
        self.userDefault = UserDefaults.standard
        
        self.player = ShuttlePlayer()
        self.player.position = Tools.fromSceneToWorldPosition(screenSpacePos: CGPoint(x: 0.5, y: 0.2))
        
        self.starsGenerator_topLayer = StarsGenerator(scene: self,
                                                 screenSize: self.size,
                                                 starsPercent: 8,
                                                 opacityRange: Tools.Interval(min: 40, max: 70),
                                                 starSize: CGSize(width: 1, height: 2),
                                                 speedFactor: 1.0)
        
        self.starsGenerator_bottomLayer = StarsGenerator(scene: self,
                                                    screenSize: self.size,
                                                    starsPercent: 13,
                                                    opacityRange: Tools.Interval(min: 30, max: 50),
                                                    starSize: CGSize(width: 1, height: 1),
                                                    speedFactor: 0.4)
        
        self.shuttle_enemy_generator = ShuttleEnemyGenerator(scene: self, target: player)
        self.asteroids_generator = AsteroidsGenerator(scene: self)

        self.gameOver_screen = GameOverScreen(scene: self)
        self.welcome_screen = WelcomeScreen(scene: self)
        self.welcome_screen.show()
        self.state = GameState.welcome
    }
    
    func start() {
        self.score = 0
        self.addChild(player)
        self.state = GameState.play
        self.score_label.isHidden = false
        self.player.direction = CGVector.zero
        self.asteroids_generator.enable = true
        self.shuttle_enemy_generator.enable = true
        self.player.lifeBar.value = self.player.stats.defense
        self.player.position = Tools.fromSceneToWorldPosition(
            screenSpacePos: CGPoint(x: 0.5, y: 0.2))
    }
    
    
    
    func touchDown(atPoint pos : CGPoint) {
        if(self.state == GameState.play) {
            if(pos.x < self.size.width/2){
                player.direction.dx = -self.PLAYER_MOVING_SPEED
            } else if(pos.x > self.size.width/2) {
                player.direction.dx = self.PLAYER_MOVING_SPEED
            }
        }
    }
    
    func touchMoved(toPoint pos : CGPoint) {
        
    }
    
    func touchUp(atPoint pos : CGPoint) {
        if self.state == GameState.play { player.direction.dx = 0 }
        else if self.state == GameState.welcome { self.welcome_screen.touchUp(pos) }
        else { gameOver_screen.touchUp(pos) }
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        for t in touches { self.touchDown(atPoint: t.location(in: self)) }
    }
    
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        for t in touches { self.touchUp(atPoint: t.location(in: self)) }
    }
    
    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent?) {
        for t in touches { self.touchUp(atPoint: t.location(in: self)) }
    }
    
    
    override func update(_ currentTime: TimeInterval) {
        starsGenerator_topLayer.generate()
        starsGenerator_bottomLayer.generate()
        shuttle_enemy_generator.generate(currentTime)
        asteroids_generator.generate(currentTime)
        
        var itemsToDelete:[SKNode] = []
        for item in self.children {
            if let movingItem = item as? MovingItem {
                movingItem.update(currentTime)
                if(movingItem.isOutOfScreen(screenSize: self.size)) {
                    itemsToDelete.append(item)
                }
                else if let collisionableItem = item as? Collisionable {
                    let overlapsedItems = overlapsListItems(collisionableItem)
                    for ovItem in overlapsedItems {
                        collisionableItem.inCollisionWith(item: ovItem)
                        ovItem.inCollisionWith(item: collisionableItem)
                    }
                }
            }
        }
        self.removeChildren(in: itemsToDelete)
    }
    
    
    func overlapsListItems(_ item: Collisionable) -> [Collisionable] {
        var list: [Collisionable] = []
        for i in self.children {
            if let child = i as? Collisionable {
                if(item as! MovingItem == child as! MovingItem) {continue}
                if item.isOverlaps(child) { list.append(child) }
            }
        }
        return list
    }
    
    
    
    func gameOver() {
        self.shuttle_enemy_generator.enable = false
        self.asteroids_generator.enable = false
        self.score_label.isHidden = true
        self.state = GameState.gameOver
        self.gameOver_screen.show()
        Tools.saveData_addScore(userDefault: self.userDefault, score: self.score)
    }
    
    
    
    func switchScreen(_ state: GameState) {
        if state == GameState.gameOver {
            self.state = state
            self.gameOver_screen.show()
        } else if state == GameState.welcome {
            self.state = state
            self.welcome_screen.show()
        }
    }
}


















