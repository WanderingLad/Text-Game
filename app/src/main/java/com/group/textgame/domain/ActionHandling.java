//package com.group.textgame.domain;
//
//import android.util.Log;
//import android.view.View;
//
//import com.group.textgame.R;
//import com.group.textgame.fragments.MainScreenFragment;
//
//public class ActionHandling extends MainScreenFragment {
//
//    public void switchAction(String text){
//        switch(text){
//            case "Move":
//                currentText = getResources().getStringArray(R.array.room_array);
//                actionText.setText(currentText[0]);
//                return;
//            case "Look":
//                textBox.append(activeRoom.getLookText() + "\n");
//                return;
//            case "Inventory":
//                currentText = getResources().getStringArray(R.array.room_array);
//                actionText.setText(currentText[0]);
//                return;
//            case "Search":
//                currentText = getResources().getStringArray(R.array.room_array);
//                actionText.setText(currentText[0]);
//                return;
//            case "Attack":
//                attackEnemy();
//                return;
//            case "North":
//                if(checkNorth()){
//                    currentText = getResources().getStringArray(R.array.main_array);
//                    actionText.setText(currentText[0]);
//                }
//                return;
//            case "South":
//                if(checkSouth()){
//                    currentText = getResources().getStringArray(R.array.main_array);
//                    actionText.setText(currentText[0]);
//                }
//                break;
//            case "East":
//                if(checkEast()){
//                    currentText = getResources().getStringArray(R.array.main_array);
//                    actionText.setText(currentText[0]);
//                }
//                return;
//            case "West":
//                if(checkWest()){
//                    currentText = getResources().getStringArray(R.array.main_array);
//                    actionText.setText(currentText[0]);
//                }
//                return;
//            default:
//                return;
//        }
//    }
//
//    private boolean checkNorth(){
//        if(activeRoom.getNorthRoom() != 0){
//            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getNorthRoom()));
//            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
//
//            return true;
//        }
//        return false;
//    }
//
//    private boolean checkSouth(){
//        if(activeRoom.getSouthRoom() != 0){
//            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getSouthRoom()));
//            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
//
//            return true;
//        }
//
//        return false;
//    }
//
//    private boolean checkEast(){
//        if(activeRoom.getEastRoom() != 0){
//            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getEastRoom()));
//            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
//
//            return true;
//        }
//
//        return false;
//    }
//
//    private boolean checkWest(){
//        if(activeRoom.getWestRoom() != 0){
//            mainViewModel.setActiveRoom(mainViewModel.getRoom(activeRoom.getWestRoom()));
//            mainViewModel.setActiveEnemy(mainViewModel.getActiveRoomEnemy());
//
//            return true;
//        }
//
//        return false;
//    }
//
//    private void attackEnemy(){
//        if(activeEnemy.getHealth() >= activePlayer.getDamage()){
//            activePlayer.attackTarget(activeEnemy);
//            mainViewModel.setEnemyHealth();
//        }
//    }
//
//    private void attackPlayer(View view){
//        if(activePlayer.getHealth() >= activeEnemy.getDamage()){
//            activeEnemy.attackTarget(activePlayer);
//            mainViewModel.setPlayerHealth();
//        }
//    }
//
//    public void setRoomInfo(long room, long level) {
//        Log.d("Test", String.valueOf(room));
//        Log.d("Test", String.valueOf(level));
//        Log.d("Test", String.valueOf(roomInfo));
//        roomInfo.setText(level + " - " + room);
//    }
//
//    public void shiftRight(String label){
//        actionText.setText(label);
//    }
//
//    public void shiftLeft(String label){
//        actionText.setText(label);
//    }
//
//    public void setEnemyHealth(int text) {
//        enemyHealth.setProgress(text, true);
//    }
//
//    public void setEnemyNameText(String text) {
//        enemyNameText.setText(text);
//    }
//}
