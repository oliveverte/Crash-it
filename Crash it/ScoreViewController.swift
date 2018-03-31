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
        var scores = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        scores = scores.sorted(by: { $0.score! > $1.score! })
        let data = scores[indexPath.row]
        
        let title = "Score : " + String(data.score!)
        let formater =  DateFormatter()
        formater.dateFormat = "dd/MM/yyyy"
        let date = formater.string(from: data.date!)
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        cell.textLabel?.numberOfLines = 0
        cell.textLabel?.text = title + "\n" + date
        return cell
    }
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle != .delete { return }
        let encoded = UserDefaults.standard.object(forKey: Tools.KEY_DEFAULT_SCORES)
        var scores = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        scores.remove(at: indexPath.row)
        let newEncoded = NSKeyedArchiver.archivedData(withRootObject: scores)
        UserDefaults.standard.set(newEncoded, forKey: Tools.KEY_DEFAULT_SCORES)
         self.tableView.deleteRows(at: [indexPath], with: .automatic)
    }
}













