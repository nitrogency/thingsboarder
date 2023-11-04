# Thingsboarder

An app made for gathering basic Android telemetry data and sending it to your Thingsboarder dashboard/gateway using HTTP.

# Usage

**1. Clone the repository**

Clone the repository with your preferred method.

**2. Open the `strings.xml` file**

It can be found in `/diagnosis/app/src/main/res/values/`. Open the file either using your text editor, or by opening the whole project in Android Studio (or preferred IDE). You're looking for these two lines:
![image](https://github.com/nitrogency/thingsboarder/assets/129758495/a2e4a32b-6aa1-4741-bb81-a90e40d3e857)

Replace the FIRST (`url_address`) with the telemetry URL that Thingsboard generated for your device. You can find it here:
![image(2)](https://github.com/nitrogency/thingsboarder/assets/129758495/54bb5e52-d39b-43bf-89eb-6d51184a9e45)

Replace the SECOND (`public_address`) with a URL that you'd like to receive the device's IP address from. For example, Amazon AWS offers this here: http://checkip.amazonaws.com/. You can also use something like ipify.

**3. Launch the application**

You can either do this in Android Studio (through a virtual device or USB debugging) or on a physical device by building the `.apk` file. Here's how to build the `.apk` file using Android Studio:

Click on the square in the left corner of the editor, and select "Build Variants":
![image](https://github.com/nitrogency/thingsboarder/assets/129758495/2e0af24d-052f-44ea-8bd4-4428bf111f50)

Click on the "debug" part of the row to open a dropdown menu. Select "release":
![image](https://github.com/nitrogency/thingsboarder/assets/129758495/b792e6c1-32b0-430f-a9d4-3636808d87d9)

Once clicked, Gradle will automatically build the project. Once done, build the `.apk`, following any prompts Android Studio might show (about signing the `.apk`, for example.):
![image](https://github.com/nitrogency/thingsboarder/assets/129758495/d5c8a557-1f5f-422a-a0aa-6a74a0b0ff57)

When this is done, Android Studio will show you where the .apk was saved. All left to do now is move the file to your device!

# Weaknesses/Limitations

<li>This only supports HTTP. You can edit the code to support various message protocols, which might be more suitable for you.</li>
<li>`BackgroundService` should be less bloated and split into different classes.</li>
<li>Less global variables, more function returns.</li>
