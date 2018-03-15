//
//  Collisionable.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation

protocol Collisionable {
    var enableCollision: Bool {get}
    var already_in_collision: Bool {get}
    
    func collisionEnter(player: Player)
    func collisionLeave(player: Player)
    func initCollider()
}
