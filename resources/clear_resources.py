import os
folders = ["temp/", "image/game/", "image/genre/", "image/platform/", "image/store/", "image/tag/"]
for folder in folders:
    for file in os.listdir(folder):
        print(file)
        os.remove(folder + file)
