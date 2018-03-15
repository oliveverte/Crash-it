//
//  Collisionable.swift
//  Crash it
//
//  Created by Olivier Picard on 13/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation

protocol Collisionable {
    func collisionEnter(player: Player)
    func collisionLeave(player: Player)
}
