function dpTime(){ 
			var now = new Date(); 
			hours = now.getHours(); 
			minutes = now.getMinutes(); 
			seconds = now.getSeconds(); 
			if (hours > 12){ hours -= 12; ampm = "���� "; 
			}else{ ampm = "���� "; 
			} if (hours < 10){ hours = "0" + hours; 
			} if (minutes < 10){ minutes = "0" + minutes; 
			} if (seconds < 10){ seconds = "0" + seconds; 
			} 
			document.getElementById("dpTime").innerHTML = ampm + hours + ":" + minutes + ":" + seconds; }