import {setCookie, getCookie, clearCookie} from './cookie.js'

export class Storage {
	constructor() {
		// this.types = [
		// 	'cookie', 'session', 'token'
		// ];
		this.storageType = 'cookie'; 
	}

	setStorageType(type) {
		this.type = type;
	}
	getStorageType() {
		return this.type;
	}

	save(key, value, day=3) {
		switch(this.type) {
			case "cookie":
				setCookie(key, value, day);
			break;
			case "session":

			break;
			case "token":

			break;
			default:
				setCookie(key, value, day);
			break;
		}
	}

	get(key) {
		switch(this.type) {
			case "cookie":
				return getCookie(key);
			case "session":

			break;
			case "token":

			break;
			default:
				return getCookie(key);
		}
	}

	clear(key) {
		switch(this.type) {
			case "cookie":
				clearCookie(key);
			break;
			case "session":

			break;
			case "token":

			break;
			default:
				setCookie(key);
			break;
		}
	}
}

const storage = new Storage();
export default storage; 