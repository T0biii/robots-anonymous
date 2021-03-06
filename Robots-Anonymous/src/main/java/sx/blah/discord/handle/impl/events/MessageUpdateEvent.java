/*
 * Discord4J - Unofficial wrapper for Discord API
 * Copyright (c) 2015
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package sx.blah.discord.handle.impl.events;

import sx.blah.discord.api.Event;
import sx.blah.discord.handle.obj.IMessage;

/**
 * This event is dispatched whenever a message is edited.
 */
public class MessageUpdateEvent extends Event {

	private final IMessage oldMessage, newMessage;

	public MessageUpdateEvent(IMessage oldMessage, IMessage newMessage) {
		this.oldMessage = oldMessage;
		this.newMessage = newMessage;
	}

	/**
	 * The original message.
	 *
	 * @return The message.
	 */
	public IMessage getOldMessage() {
		return oldMessage;
	}

	/**
	 * The new message.
	 *
	 * @return The message.
	 */
	public IMessage getNewMessage() {
		return newMessage;
	}
}
