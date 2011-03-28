/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Copyright 2009, 2010, 2011 Caprica Software Limited.
 */

package uk.co.caprica.vlcj.player;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.binding.internal.libvlc_instance_t;

import com.sun.jna.Pointer;

/**
 * Base implementation for media players sharing common behaviours.
 */
public class AbstractMediaPlayer {

  /**
   * Native library interface.
   */
  protected final LibVlc libvlc;
  
  /**
   * Libvlc instance.
   */
  protected final libvlc_instance_t instance;

  /**
   * Create a media player.
   * 
   * @param libvlc native library interface
   * @param instance libvlc instance
   */
  public AbstractMediaPlayer(LibVlc libvlc, libvlc_instance_t instance) {
    this.libvlc = libvlc;
    this.instance = instance;
  }

  /**
   * Get a String from a native string pointer, freeing the native string 
   * pointer when done.
   * <p>
   * If the native string pointer is not freed then a native memory leak will
   * occur.
   * 
   * @param pointer pointer to native string, may be <code>null</code>
   * @return string, or <code>null</code> if the pointer was <code>null</code>
   */
  protected final String getNativeString(Pointer pointer) {
    if(pointer != null) {
      String result = pointer.getString(0, false);
      libvlc.libvlc_free(pointer);
      return result;
    }
    else {
      return null;
    }
  }
}
