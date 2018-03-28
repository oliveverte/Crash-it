//
//  ScoreView.swift
//  Crash it
//
//  Created by Olivier Picard on 25/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import UIKit

class ScoreViewController: UITableViewController {
    
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let encoded = UserDefaults.standard.object(forKey: Tools.KEY_DEFAULT_SCORES)
        if encoded == nil { return 0 }
        let scores = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        
        return scores.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let encoded = UserDefaults.standard.object(forKey: Tools.KEY_DEFAULT_SCORES)
        let scores = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        
        let title = "Score : " + String(scores[indexPath.row].score!)
        let formater =  DateFormatter()
        formater.dateFormat = "dd/MM/yyyy"
        let date = formater.string(from: Date())
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        cell.textLabel?.numberOfLines = 0
        cell.textLabel?.text = title + "\n" + date
        return cell
    }
}
