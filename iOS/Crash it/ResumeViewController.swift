//
//  ResumeViewController.swift
//  Crash it
//
//  Created by Olivier Picard on 28/03/2018.
//  Copyright © 2018 Olivier Picard. All rights reserved.
//

import Foundation
import UIKit


class ResumeViewController: UITableViewController {
    var clickedIndexPath: Int?
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        let encoded = UserDefaults.standard.object(forKey: Tools.KEY_DEFAULT_GAMEINFOS)
        if encoded == nil { return 0 }
        let scores = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        
        return scores.count
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let encoded = UserDefaults.standard.object(forKey: Tools.KEY_DEFAULT_GAMEINFOS)
        let scores = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        let data = scores[indexPath.row]
        
        let title = "Sauvegarde n°" + String(indexPath.row + 1)
        let formater =  DateFormatter()
        formater.dateFormat = "dd/MM/yyyy"
        let secondLine = formater.string(from: data.date!) + " Score : " + String(data.score!)
        
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        cell.textLabel?.numberOfLines = 0
        cell.textLabel?.text = title + "\n" + secondLine
        return cell
    }
    
    
    
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle != .delete { return }
        let encoded = UserDefaults.standard.object(forKey: Tools.KEY_DEFAULT_GAMEINFOS)
        var scores = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
        scores.remove(at: indexPath.row)
        let newEncoded = NSKeyedArchiver.archivedData(withRootObject: scores)
        UserDefaults.standard.set(newEncoded, forKey: Tools.KEY_DEFAULT_GAMEINFOS)
        self.tableView.deleteRows(at: [indexPath], with: .automatic)
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.clickedIndexPath = indexPath.row
        performSegue(withIdentifier: "FromResumeToScene", sender: nil)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "FromResumeToScene" {
            let encoded = UserDefaults.standard.object(forKey: Tools.KEY_DEFAULT_GAMEINFOS)
            var scores = NSKeyedUnarchiver.unarchiveObject(with: encoded as! Data) as! [Tools.SaveData]
            let data = scores[self.clickedIndexPath!]
            let gameViewController = segue.destination as! GameViewController
            gameViewController.initWithScore = data.score!
//            gameViewController.initWithItems = data.items as! [Tools.ItemConf]
        }
    }
}



















