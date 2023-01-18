package me.stitchie.legacypit.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MySQLPlayerCache {

	private MySQLPlayerCache() {

	}

	public static boolean doesUserExist(final UUID uuid) {
		try {
			final PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT UUID FROM PlayerData WHERE UUID = ?");
			statement.setString(1, uuid.toString());
			final ResultSet set = statement.executeQuery();
			return set.next();
		} catch (final SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}


	/*
	public static PlayerCache getData(final UUID uuid) {
		try {
			final PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT * FROM PlayerData WHERE UUID = ?");
			statement.setString(1, uuid.toString());
			final ResultSet result = statement.executeQuery();
			System.out.println("Result: " + result);
			while (result.next()) {
				final double gold = result.getDouble("gold");
				final int level = result.getInt("level");
				final int bounty = result.getInt("bounty");
				System.out.println("GOLD: " + gold + "LEVEL: " + level + "BOUNTY: " + bounty);
			}
		} catch (final SQLException throwables) {
			throwables.printStackTrace();
		}
		return new PlayerCache();
	}

	 */


	public static double getGold(final UUID uuid) {
		try {
			final PreparedStatement statement = MySQL.getConnection().prepareStatement("SELECT gold FROM PlayerData WHERE UUID = ?");
			statement.setString(1, uuid.toString());
			final ResultSet result = statement.executeQuery();
			while (result.next()) {
				return result.getDouble("gold");
			}
		} catch (final SQLException throwables) {
			throwables.printStackTrace();
		}
		return 0;
	}


}
