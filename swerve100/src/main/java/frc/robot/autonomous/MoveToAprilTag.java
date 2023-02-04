// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autonomous;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.Constants;
import frc.robot.localization.HashTag;
import frc.robot.subsystems.SwerveDriveSubsystem;

/** Add your docs here. */
public class MoveToAprilTag extends TrajectoryCommand {

    int tagID;
    HashTag hashTag = new HashTag();

    public MoveToAprilTag(SwerveDriveSubsystem m_robotDrive, int tD) {
        super(m_robotDrive);
        this.tagID = tD;

    }

    @Override
    public Trajectory genTrajectory(SwerveDriveSubsystem m_robotDrive) {

        Pose3d aprilPose = hashTag.getTagIDPose(tagID);

        TrajectoryConfig trajectoryConfig = new TrajectoryConfig(
                4,
                3)
                // Add kinematics to ensure max speed is actually obeyed
                .setKinematics(SwerveDriveSubsystem.kDriveKinematics);


        Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                m_robotDrive.getPose(),
                List.of(),
                new Pose2d(aprilPose.getX() - 1, aprilPose.getY(), new Rotation2d(aprilPose.getRotation().getAngle())),
                trajectoryConfig);

        return exampleTrajectory;

    }

}
